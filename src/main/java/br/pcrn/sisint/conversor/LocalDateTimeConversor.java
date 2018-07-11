package br.pcrn.sisint.conversor;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.LocalDateTimeConverter;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Convert(LocalDateTime.class)
@RequestScoped
@Specializes
public class LocalDateTimeConversor extends LocalDateTimeConverter{

    /** @deprecated */
    protected  LocalDateTimeConversor(){
        this(null);
    }

    @Inject
    public LocalDateTimeConversor(Locale locale) {
        super(locale);
    }

    @Override
    protected DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ISO_DATE_TIME;
    }
}
