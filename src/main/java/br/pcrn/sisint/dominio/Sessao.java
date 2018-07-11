package br.pcrn.sisint.dominio;


import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Locale;

public abstract class Sessao implements Serializable {
    protected HttpSession httpSession;
    private Locale locale;

    @Inject
    private HttpServletRequest httpServletRequest;

    public Sessao(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public Sessao() {
        this((HttpSession)null);
    }

    public void destruir() {
        this.httpSession.invalidate();
        this.httpServletRequest.getSession(true);
    }

    public Locale getLocale() {
        this.locale = Locale.getDefault();
        return this.locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}