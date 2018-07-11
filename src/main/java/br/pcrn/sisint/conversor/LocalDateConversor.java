package br.pcrn.sisint.conversor;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.LocalDateConverter;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Convert(LocalDate.class)
@RequestScoped
@Specializes
public class LocalDateConversor extends LocalDateConverter{

    /** @deprecated */
    protected  LocalDateConversor(){
        this(null);
    }

    @Inject
    public LocalDateConversor(Locale locale) {
        super(locale);
    }

    @Override
    protected DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ISO_LOCAL_DATE;
    }

    @Override
    public LocalDate convert(String value, Class<? extends LocalDate> type) {
        String valores[] = value.split("/");
        if(valores.length == 3) {
            value = valores[2] + "-" + valores[1] + "-" + valores[0];
        }
        return super.convert(value, type);
    }
}