package nl.kaninefatendreef.si.document;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;


/**
 * TODO verwijder
 * @author patrick
 *
 */
public class JaxbContextCache {

    @SuppressWarnings("rawtypes")
	private static Map<Class, JAXBContext> cache = new HashMap<Class, JAXBContext>();

    public static synchronized JAXBContext getInstance(@SuppressWarnings("rawtypes") Class klasse) throws JAXBException {

        if (!cache.containsKey(klasse)) {
            cache.put(klasse, JAXBContext.newInstance(klasse));
        }

        return cache.get(klasse);
    }
}
