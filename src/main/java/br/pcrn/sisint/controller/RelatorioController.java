package br.pcrn.sisint.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.download.Download;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.pcrn.sisint.anotacoes.Seguranca;
import br.pcrn.sisint.dominio.TipoUsuario;
import br.pcrn.sisint.negocio.ServicosNegocio;
import javax.inject.Inject;


@Controller
public class RelatorioController extends Controlador{

    ServicosNegocio servicosNegocio;

    public RelatorioController () { this(null, null); }

    @Inject
    public RelatorioController(Result resultado, ServicosNegocio servicosNegocio) {
        super(resultado);
        this.servicosNegocio = servicosNegocio;
    }

    @Post
    @Seguranca(tipoUsuario = TipoUsuario.ADMINISTRADOR)
    public Download relatorioNumServicosPorSetor(String dtDe, String dtAte) {
        Download download = null;

        try {
            download = servicosNegocio.relatorioNumServicosPorSetor(dtDe, dtAte);
        } catch (Exception e) {
            resultado.include("mensagem", new SimpleMessage("error", "mensagem.relatorioServico.dataVazia.error"));
            resultado.redirectTo(ServicosController.class).formRelatorio();
        }
        return download;
    }


    public Download relatorioFiltrarServicosPorSetor(Long idSetor, String dtDe, String dtAte) {
        Download download = null;

        try {
            download = servicosNegocio.relatorioFiltrarServicosPorSetor(idSetor, dtDe, dtAte);
        } catch (Exception e) {
            resultado.include("mensagem", new SimpleMessage("error", "mensagem.relatorioServico.dataVazia.error"));
            resultado.redirectTo(ServicosController.class).formRelatorio();
        }
        return download;
    }

}
