package org.vento.sentiment.classification;

import gate.Annotation;
import gate.Document;
import gate.Factory;
import gate.util.DocumentProcessor;
import gate.util.GateException
import org.apache.commons.lang.StringEscapeUtils
import org.apache.commons.lang.StringUtils;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: lfoppiano
 * Date: 02/05/13
 * Time: 11:49
 * To change this template use File | Settings | File Templates.
 */
public class ClassificationWrapper {

    private DocumentProcessor classifier;

    public String classify(String text) {

        Document tmpDocument = null;

        text = StringEscapeUtils.escapeXml(text)
        float result = 0.0f;
        try {
            tmpDocument = Factory.newDocument(("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<twit>\n<text>" + text + "</text>\n</twit>"))
            classifier.processDocument(tmpDocument);

            Iterator<Annotation> classificationScoreStr = tmpDocument.getAnnotations("Output").get("Review").iterator()
            if (classificationScoreStr.hasNext()) {
                result = Float.parseFloat((String) classificationScoreStr.next().getFeatures().get("score"))
            }
            Factory.deleteResource(tmpDocument);
        } catch (GateException e) {
            e.printStackTrace();
        } finally {
            Factory.deleteResource(tmpDocument);
            tmpDocument = null;
        }

        return result;
    }

    public void setClassifier(DocumentProcessor classifier) {
        this.classifier = classifier;
    }


}
