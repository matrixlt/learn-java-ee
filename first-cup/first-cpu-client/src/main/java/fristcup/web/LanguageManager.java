package fristcup.web;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

@Named
@SessionScoped
public class LanguageManager implements Serializable {

    private static final Logger logger = Logger.getLogger(LanguageManager.class.getName());
    private static final Map<String, Locale> locales;
    private static final Map<String, Locale> toStringValueToLocales;
    
    private String currentLocale;
    private String currentLocaleInBCP47; // Locale name in BCP47

    static {
        locales = new LinkedHashMap<>();
        locales.put("English(United States)", Locale.US);
        locales.put("简体中文", Locale.SIMPLIFIED_CHINESE);
        toStringValueToLocales = new LinkedHashMap<>();
        locales.entrySet().forEach((entry) -> {
            toStringValueToLocales.put(entry.getValue().toString(), entry.getValue());
        });
    }

    public String getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(String locale) {
        this.currentLocale = locale;
    }

    public static Map<String, Locale> getLocales() {
        return locales;
    }

    public String getCurrentLocaleInBCP47() {
        return currentLocaleInBCP47;
    }

    public void changeLocale(ValueChangeEvent e) {
        String name = (String) e.getNewValue();
        Locale locale = toStringValueToLocales.get(name);
        if (locale != null) {
            this.currentLocale = name;
            this.currentLocaleInBCP47 = name.replace('_', '-');
            FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
            logger.log(Level.SEVERE, "Change locale to {0}", locale);
        } else {
            logger.log(Level.SEVERE, "Locale cast error");
        }
    }
}
