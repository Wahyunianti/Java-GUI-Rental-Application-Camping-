/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BfReader;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wahyu
 */
public class ClassCetakPdf {
    
    String nama, judul, isi, total, bayar, kembali;
    
   
        public void CetakTabel2(String nmDokumen,String jedaDokumen, String pathGambar, String[] judulTabel, String[][] isi, int bar, int kol) throws IOException{
        try {
            nama = nmDokumen + ".pdf";
            judul = jedaDokumen;
            
            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            
            Document document = new Document();
            PdfWriter pw = PdfWriter.getInstance(document, new FileOutputStream(nama));
            PdfPTable pt = new PdfPTable(kol);
            document.open();
            
            Image img;
            img = Image.getInstance(pathGambar);
            img.setAlignment(Image.MIDDLE);
            img.scaleToFit(document.getPageSize().getWidth(), document.getPageSize().getHeight());
            document.add(img);            
            
            Paragraph p = new Paragraph(judul, boldFont);
            p.setAlignment(Paragraph.ALIGN_CENTER);
            p.setSpacingAfter(30);
            document.add(p);
            
            for(int i = 0; i < kol; i++){
                    pt.addCell(judulTabel[i]);                
            }
            for(int i = 0; i < bar; i++){
                for (int j =0; j < kol; j++){
                    pt.addCell(isi[i][j]);  
                }
            }
            
            document.add(pt);            
            document.close();
            System.out.println("Sukses Cetak File...");
        } catch (FileNotFoundException | DocumentException ex) {
             Logger.getLogger(ClassCetakPdf.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
        
        
    public void CetakTabelTrans(String nmDokumen,String jedaDokumen, String pathGambar, String[] judulTabel, String[][] isi, int bar, int kol, String byr, String kbl, String ttl) throws IOException{
        try {
            nama = nmDokumen + ".pdf";
            judul = jedaDokumen;
            total = ttl;
            kembali = kbl;
            bayar = byr;  
            
            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);            
            Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.ITALIC);
            
            Document document = new Document();
            PdfWriter pw = PdfWriter.getInstance(document, new FileOutputStream(nama));
            PdfPTable pt = new PdfPTable(kol);
            document.open();
            
            Image img;
            img = Image.getInstance(pathGambar);
            img.setAlignment(Image.MIDDLE);
            img.scaleToFit(document.getPageSize().getWidth(), document.getPageSize().getHeight());
            document.add(img);            
            
            Paragraph p = new Paragraph(judul, boldFont);
            p.setAlignment(Paragraph.ALIGN_CENTER);
            p.setSpacingAfter(30);
            document.add(p);
            
            for(int i = 0; i < kol; i++){
                    pt.addCell(judulTabel[i]);                
            }
            for(int i = 0; i < bar; i++){
                for (int j =0; j < kol; j++){
                    pt.addCell(isi[i][j]);  
                }
            }
            
            document.add(pt);  
                        
            Paragraph a = new Paragraph(bayar);
            a.setTabSettings(new TabSettings(56f));
            a.setAlignment(Paragraph.ALIGN_MIDDLE);
            a.setSpacingBefore(20);
            document.add(a);
            
            Paragraph b = new Paragraph(kembali);
            b.setTabSettings(new TabSettings(56f));
            b.setAlignment(Paragraph.ALIGN_MIDDLE);
            b.setSpacingAfter(10);
            document.add(b);
                        
            Paragraph c = new Paragraph(total, normalFont);
            c.setTabSettings(new TabSettings(56f));
            c.setAlignment(Paragraph.ALIGN_MIDDLE);
            c.setSpacingBefore(25);
            document.add(c);
                        
            document.close();
            System.out.println("Sukses Cetak File...");
        } catch (FileNotFoundException | DocumentException ex) {
             Logger.getLogger(ClassCetakPdf.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
