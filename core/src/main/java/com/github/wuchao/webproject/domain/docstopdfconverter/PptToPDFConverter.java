package com.github.wuchao.webproject.domain.docstopdfconverter;

import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.SlideShow;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PptToPDFConverter extends PptxToPDFConverter {

	private Slide[] slides;
	
	public PptToPDFConverter(InputStream inStream, OutputStream outStream, boolean showMessages, boolean closeStreamsWhenComplete) {
		super(inStream, outStream, showMessages, closeStreamsWhenComplete);
	}


	@Override
	protected Dimension processSlides() throws IOException {

		SlideShow ppt = new SlideShow(inStream);
		Dimension dimension = ppt.getPageSize();
		slides = ppt.getSlides();
		return dimension;
	}
	
	@Override
	protected int getNumSlides(){
		return slides.length;
	}
	
	@Override
	protected void drawOntoThisGraphic(int index, Graphics2D graphics){
		slides[index].draw(graphics);
	}
	
	@Override
	protected Color getSlideBGColor(int index){
		return slides[index].getBackground().getFill().getForegroundColor();
	}

}
