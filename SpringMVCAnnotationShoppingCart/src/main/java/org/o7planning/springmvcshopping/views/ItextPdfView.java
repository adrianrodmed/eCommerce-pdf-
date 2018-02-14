package org.o7planning.springmvcshopping.views;

import java.text.DateFormat;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.o7planning.springmvcshoppingcart.model.CartInfo;
import org.o7planning.springmvcshoppingcart.model.CartLineInfo;
import org.o7planning.springmvcshoppingcart.util.Utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ItextPdfView extends AbstractITextPdfView {

    private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance(DateFormat.SHORT);

    @Override
    protected void buildPdfDocument(Map<String, Object> model,
                                    Document document, PdfWriter writer, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        @SuppressWarnings("unchecked")
//        List<CartInfo> cartinfos = (List<CartInfo>) model.get("cartinfos");
        CartInfo cartinfo = Utils.getCartInSession(request);
        PdfPTable table = new PdfPTable(4);
        table.setWidths(new int[]{10, 40, 40, 10});

        table.addCell("Code");
        table.addCell("Nombre de usuario");
        table.addCell("Nombre de producto");
        table.addCell("Precio");
        
        for (CartLineInfo cInfo : cartinfo.getCartLines()){
        	table.addCell(String.valueOf(cInfo.getProductInfo().getCode()));
    		table.addCell(String.valueOf(cartinfo.getCustomerInfo().getName()));
    		table.addCell(String.valueOf(cInfo.getProductInfo().getName()));
    		table.addCell(String.valueOf(cInfo.getProductInfo().getPrice()));
        }

        document.add(table);
    }

}