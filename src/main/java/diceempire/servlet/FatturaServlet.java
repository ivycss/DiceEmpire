package diceempire.servlet;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import diceempire.model.*;
import diceempire.control.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

public class FatturaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idOrdine = Integer.parseInt(request.getParameter("idOrdine"));

        OrdineDettagliModelMD ordineDettagliModel = new OrdineDettagliModelMD();
        OrdineModelMD ordineModel = new OrdineModelMD();
        Ordine ordine = null;
        Ordine ordine1 = null;
        try {
            ordine = ordineDettagliModel.doRetrieveByKey(idOrdine);
            ordine1 = ordineModel.doRetrieveByKey(idOrdine);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante il recupero dei dettagli dell'ordine.");
            return;
        }

        if (ordine == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ordine non trovato.");
            return;
        }

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=fattura_" + idOrdine + ".pdf");

        try (OutputStream out = response.getOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            // Aggiungi il logo
            String logoPath = getServletContext().getRealPath("/images/logode.png");
            Image logo = Image.getInstance(logoPath);
            logo.setAlignment(Element.ALIGN_LEFT);
            logo.scaleToFit(100, 100); 
            document.add(logo);

            document.add(new Paragraph("Fattura per Ordine #" + idOrdine));
            document.add(new Paragraph(" ")); 

            PdfPTable table = new PdfPTable(5); 
            table.setWidthPercentage(100);
            addTableHeader(table);
            addRows(table, ordine.getProdottiOrdine());

            document.add(table);
            document.add(new Paragraph(" ")); 

            double totaleOrdine = ordine.getProdottiOrdine().stream()
                    .mapToDouble(p -> p.getPrezzo() * p.getNumItems())
                    .sum();
            document.add(new Paragraph("Totale Ordine: Euro " + totaleOrdine));
            document.add(new Paragraph("Data Ordine: " + ordine1.getDataOrdine()));
            document.add(new Paragraph("Grazie per aver scelto DiceEmpire"));

            document.close();
        } catch (DocumentException e) {
            throw new IOException("Errore durante la creazione del PDF: " + e.getMessage(), e);
        }
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Nome", "Quantita' ", "Prezzo Unitario", "IVA", "Totale")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setPhrase(new Paragraph(columnTitle));
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, List<ProdottoInCarrello> prodotti) {
        for (ProdottoInCarrello prodotto : prodotti) {
            table.addCell(prodotto.getNome());
            table.addCell(String.valueOf(prodotto.getNumItems()));
            table.addCell("Euro  " + prodotto.getPrezzo());

            
            BigDecimal ivaPercent = BigDecimal.valueOf((prodotto.getIVA() - 1) * 100).setScale(0, RoundingMode.HALF_UP);
            table.addCell(ivaPercent + "%");

            table.addCell("Euro  " + (prodotto.getPrezzo() * prodotto.getNumItems()));
        }
    }
}