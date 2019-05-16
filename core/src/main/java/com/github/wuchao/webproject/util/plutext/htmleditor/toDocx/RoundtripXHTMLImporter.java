/*
    This file is part of docx-html-editor.

	docx-html-editor is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.github.wuchao.webproject.util.plutext.htmleditor.toDocx;

import com.github.wuchao.webproject.util.plutext.htmleditor.PPrNone;
import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.org.xhtmlrenderer.render.BlockBox;
import org.docx4j.wml.PPr;
import org.w3c.dom.css.CSSValue;

import java.util.Map;

public class RoundtripXHTMLImporter extends XHTMLImporterImpl {

    public RoundtripXHTMLImporter(WordprocessingMLPackage wordMLPackage) {
        super(wordMLPackage);
    }

    @Override
    protected PPr getPPr(BlockBox blockBox, Map<String, CSSValue> cssMap) {

        // if the paragraph has an ID, use the preserved existing pPr
        String id = blockBox.getElement().getAttribute("id");

        if (id == null) {
            log.debug("no id on p ");

        } else {
            log.debug("processing p with id " + id);

            Object o = wordMLPackage.getUserData(id);
            if (o == null) {

                log.debug("no #Pr UserData on p with id " + id);

            } else if (o instanceof PPrNone) {
                return null;
            } else {

                return ((PPr) o);
                // (TODO unless the user has changed the style)
            }
        }

        // A new p the user has created
        PPr pPr = Context.getWmlObjectFactory().createPPr();
        populatePPr(pPr, blockBox, cssMap);
        return pPr;
    }

}
