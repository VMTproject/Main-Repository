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
import main.Note;

public class PrintToPDF {
	public static final float MM_TO_POINT =2.834645669f ;
	public static void Print(Document document, String[] head, Note[] body) throws IOException {
	    
	    PdfFont fontKai = PdfFontFactory.createFont("resources/kai.ttf", PdfEncodings.IDENTITY_H,
	    		true);
	    PdfFont fontSong = PdfFontFactory.createFont("resources/song.ttf", PdfEncodings.IDENTITY_H,
	    		true);
	    PdfFont fontJP = PdfFontFactory.createFont("resources/EUDC.ttf", PdfEncodings.IDENTITY_H,
	    		true);
	    
	    Paragraph title = new Paragraph(head[0]);
	    title.setFont(fontKai).setFontSize(6.25f * MM_TO_POINT); //25 quarts
	    title.setCharacterSpacing(1.5f * MM_TO_POINT);
	    
	    Paragraph subtitle = new Paragraph(head[1]);
	    subtitle.setFont(fontSong).setFontSize(5 * MM_TO_POINT); //20 quarts
	    
	    Paragraph author = new Paragraph(head[2] + "¡¡´Ê");
	    author.setFont(fontSong).setFontSize(3.75f * MM_TO_POINT); //15 quarts
	    
	    Paragraph composer = new Paragraph(head[3] + "¡¡Çú");
	    composer.setFont(fontSong).setFontSize(3.75f * MM_TO_POINT); //15 quarts
	    
	    String temporaryKey = head[4];
	    temporaryKey.replace('b', '\u266D');//½µºÅ
	    temporaryKey.replace('#', '\u266F');//ÉýºÅ
	    Paragraph key = new Paragraph("1=" + temporaryKey);
	    key.setFont(fontSong).setFontSize(3.75f * MM_TO_POINT); //15 quarts
	    
	    Paragraph tempoLine = new Paragraph("¡ª");
	    tempoLine.setFont(fontSong).setFontSize(3.75f * MM_TO_POINT); //15 quarts
	    Paragraph tempoN = new Paragraph(head[5]);
	    tempoN.setFont(fontSong).setFontSize(2.75f * MM_TO_POINT); //10 quarts
	    Paragraph tempoD = new Paragraph(head[6]);
	    tempoD.setFont(fontSong).setFontSize(2.75f * MM_TO_POINT); //10 quarts
	    
	    String tempoNote = "";
	    switch (head[7].trim()) {
	    case "2": tempoNote = "\u2668";
	    break;
	    case "3": tempoNote = "\u2668" + ".";
	    break;
	    case "4": tempoNote = "\u2669";
	    break;
	    case "6": tempoNote = "\u2669" + ".";
	    break;
	    case "8": tempoNote = "\u266A";
	    break;
	    case "12": tempoNote = "\u266A" + ".";
	    break;
	    }
	    Paragraph tempo = new Paragraph(tempoNote + "=" + head[8]);
	    tempo.setFont(fontSong).setFontSize(3.75f * MM_TO_POINT); //15 quarts
	    
	    document.showTextAligned(title, 105 * MM_TO_POINT, 277 * MM_TO_POINT,
	    		TextAlignment.CENTER, VerticalAlignment.TOP);
	    document.showTextAligned(subtitle, 105 * MM_TO_POINT, 268.5f * MM_TO_POINT,
	    		TextAlignment.CENTER, VerticalAlignment.TOP);
	    document.showTextAligned(author, 190 * MM_TO_POINT, 262.75f * MM_TO_POINT,
	    		TextAlignment.RIGHT, VerticalAlignment.TOP);
	    document.showTextAligned(composer, 190 * MM_TO_POINT, 257 * MM_TO_POINT,
	    		TextAlignment.RIGHT, VerticalAlignment.TOP);
	    document.showTextAligned(key, 20 * MM_TO_POINT, 262.75f * MM_TO_POINT,
	    		TextAlignment.LEFT, VerticalAlignment.TOP);
	    document.showTextAligned(tempoLine, 32 * MM_TO_POINT, 261 * MM_TO_POINT,
	    		TextAlignment.CENTER, VerticalAlignment.MIDDLE);
	    document.showTextAligned(tempoN, 32 * MM_TO_POINT, 260.75f * MM_TO_POINT,
	    		TextAlignment.CENTER);
	    document.showTextAligned(tempoD, 32 * MM_TO_POINT, 261 * MM_TO_POINT,
	    		TextAlignment.CENTER, VerticalAlignment.TOP);
	    document.showTextAligned(tempo, 20 * MM_TO_POINT, 257 * MM_TO_POINT,
	    		TextAlignment.LEFT, VerticalAlignment.TOP);
	    
	    String note, noteTrans = "";
	    Paragraph notePrint;
	    for (int i=1; i <= body.length; i++) {
	    	if(body[i-1] == null) {
	    		break;
	    	}
	    	if(i > 1) {
	    		if(body[i-2].page + 1 == body[i-1].page) {
		    		document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
		    	}
	    	}
	    	note = body[i-1].content;
	    	switch(note) {
	    	case "!": 	noteTrans = "\uE0A6";
	    				break;
	    	case "\u0024": noteTrans = "\uE09D";
						break;
	    	case "\u0025": noteTrans = "\uE0A0";
						break;
	    	case "0": 	noteTrans = "\uE2E2";
						break;
	    	case "1": 	noteTrans = "\uE2E3";
						break;
	    	case "2": 	noteTrans = "\uE2E4";
						break;
	    	case "3": 	noteTrans = "\uE2E5";
						break;
	    	case "4": 	noteTrans = "\uE2E6";
						break;
	    	case "5": 	noteTrans = "\uE2E7";
						break;
	    	case "6": 	noteTrans = "\uE2E8";
						break;
	    	case "7": 	noteTrans = "\uE2E9";
						break;
	    	case "D": 	noteTrans = "\uE0B5";
						break;
	    	case "G": 	noteTrans = "\uE0C7";
						break;
	    	case "H": 	noteTrans = "\uE09C";
						break;
	    	case "\\": 	noteTrans = "\uE0E7";
						break;
	    	case "|": 	noteTrans = "\uE0C8";
						break;
	    	case "": 	break;
	    	}
	    	notePrint = new Paragraph(noteTrans);
	    	notePrint.setFont(fontJP).setFontSize(3.25f * MM_TO_POINT); //13 quarts
	    	document.showTextAligned(notePrint, (float)body[i-1].coordinateX * MM_TO_POINT,
	    			(float)body[i-1].coordinateY * MM_TO_POINT, TextAlignment.LEFT,
	    			VerticalAlignment.TOP);
	    }
	    System.out.println("Êä³öPDF³É¹¦¡£");
	}
	
	public void printMain(String location, PageInfo page, HeadInfo head)
			throws IOException {
		PdfWriter writer = new PdfWriter(location);
		PdfDocument pdf = new PdfDocument(writer);
		pdf.setDefaultPageSize(new PageSize((float)page.pageWidth, (float)page.pageHeight));
		Document document = new Document(pdf);
		
		document.close();
		System.out.println("TINA is stupid!!!");
	}
}
