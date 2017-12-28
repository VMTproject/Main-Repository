package io;

import java.io.IOException;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import formats.HeadInfo;
import formats.PageInfo;

public class write {
	public static final float MM_TO_POINT =2.834645669f ;
	public static void writeToPDF(String location, PageInfo page, HeadInfo head)
			throws IOException {
		PdfWriter writer = new PdfWriter(location);
		PdfDocument pdf = new PdfDocument(writer);
		pdf.setDefaultPageSize(new PageSize((float)page.pageWidth * MM_TO_POINT,
				(float)page.pageHeight * MM_TO_POINT));
		Document document = new Document(pdf);
		PdfFont fontOne = PdfFontFactory.createFont("C:/windows/fonts/方正大标宋_GBK.ttf",
				PdfEncodings.IDENTITY_H, true);
		PdfFont fontTwo = PdfFontFactory.createFont("C:/windows/fonts/SimHei.ttf",
				PdfEncodings.IDENTITY_H, true);
		PdfFont fontThree = PdfFontFactory.createFont("C:/windows/fonts/StSong.ttf",
				PdfEncodings.IDENTITY_H, true);
		PdfFont fontJP = PdfFontFactory.createFont("resources/EUDC.ttf", PdfEncodings.IDENTITY_H,
	    		true);
		
		writeHead(document, head, fontOne, fontTwo, fontThree, fontJP, page.titleSize,
				page.subtitleSize, page.headerSize);
		
		System.out.println(head.getNoteStart(15, 13));
		
		document.close();
	    
	    System.out.println("输出PDF成功。");
	}
	public static void writeHead(Document document, HeadInfo head,
			PdfFont titleFont, PdfFont subtitleFont, PdfFont headFont, PdfFont JPFont,
			double titleSize, double subtitleSize, double headSize) {
		if(!(head.title.equals(""))) {
			document.showTextAligned(new Paragraph(head.title).setFont(titleFont)
					.setFontSize((float)titleSize * MM_TO_POINT / 4)
					.setCharacterSpacing(1.5f * MM_TO_POINT), head.titleXY[0] * MM_TO_POINT,
					head.titleXY[1] * MM_TO_POINT, TextAlignment.CENTER, VerticalAlignment.TOP);
		}
		if(!(head.subtitle.equals(""))) {
			document.showTextAligned(new Paragraph(head.subtitle).setFont(subtitleFont)
					.setFontSize((float)subtitleSize * MM_TO_POINT / 4),
					head.subtitleXY[0] * MM_TO_POINT, head.subtitleXY[1] * MM_TO_POINT,
					TextAlignment.CENTER, VerticalAlignment.TOP);
		}
		if(!(head.poet.equals(""))) {
			document.showTextAligned(new Paragraph(head.poet).setFont(headFont)
					.setFontSize((float)headSize * MM_TO_POINT / 4),
					head.poetXY[0] * MM_TO_POINT, head.poetXY[1] * MM_TO_POINT,
					TextAlignment.RIGHT, VerticalAlignment.TOP);
		}
		if(!(head.composer.equals(""))) {
			document.showTextAligned(new Paragraph(head.composer).setFont(headFont)
					.setFontSize((float)headSize * MM_TO_POINT / 4),
					head.composerXY[0] * MM_TO_POINT, head.composerXY[1] * MM_TO_POINT,
					TextAlignment.RIGHT, VerticalAlignment.TOP);
		}
		if(!(head.key.equals(""))) {
			document.showTextAligned(new Paragraph(head.key).setFont(headFont)
					.setFontSize((float)headSize * MM_TO_POINT / 4),
					head.keyXY[0] * MM_TO_POINT, head.keyXY[1] * MM_TO_POINT,
					TextAlignment.LEFT, VerticalAlignment.TOP);
		}
		if(head.beat != 0x00) {
			int nume = head.beat / 4;
			int deno = head.beat % 4;
			
			String b1 = "\uE225\uE226\uE227\uE228\uE229\uE22A\uE22B\uE22C\uE22D\uE22E"
					.substring(nume, nume+1);
			String b2 = " \uE1F9\uE1FB\uE1FF".substring(deno, deno+1);
			
			document.showTextAligned(new Paragraph(b1).setFont(JPFont)
					.setFontSize((float)headSize * MM_TO_POINT / 5),
					head.beatXY[0] * MM_TO_POINT,
					(head.beatXY[1] + (float)headSize * 0.09f) * MM_TO_POINT,
					TextAlignment.LEFT, VerticalAlignment.TOP);
			document.showTextAligned(new Paragraph(b2).setFont(JPFont)
					.setFontSize((float)headSize * MM_TO_POINT / 5),
					head.beatXY[0] * MM_TO_POINT,
					(head.beatXY[1] - (float)headSize * 0.09f) * MM_TO_POINT,
					TextAlignment.LEFT, VerticalAlignment.TOP);
		}
		if(head.tempo != 0) {
			document.showTextAligned(new Paragraph("\uE0BC").setFont(JPFont)
					.setFontSize((float)headSize * MM_TO_POINT / 4),
					head.tempoXY[0] * MM_TO_POINT, head.tempoXY[1] * MM_TO_POINT,
					TextAlignment.LEFT, VerticalAlignment.TOP);
			document.showTextAligned(new Paragraph("" + head.tempo).setFont(headFont)
					.setFontSize((float)headSize * MM_TO_POINT / 4),
					(head.tempoXY[0] + (float)headSize / 4) * MM_TO_POINT,
					head.tempoXY[1] * MM_TO_POINT, TextAlignment.LEFT, VerticalAlignment.TOP);
		}
	}
}
