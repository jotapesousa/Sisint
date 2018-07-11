package br.pcrn.sisint.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import br.com.caelum.vraptor.jasperreports.Report;
import br.pcrn.sisint.dominio.Entidade;
import br.pcrn.sisint.dominio.Termo;

@SuppressWarnings("rawtypes")
public class ReportJasperServico<T extends Entidade> implements Report{

    private final List<T> data;
    private Map<String, Object> parameters;
    private String template;

    public ReportJasperServico(List<T> data, String template, ServletContext context) {

        this.data = data;
        this.template = template;
        this.parameters = new HashMap<String, Object>();
        addParameter("diretorio_base", context.getRealPath("/") );
//        addParameter("termo",data);

    }

    @Override
    public String getTemplate() {
        return template;
    }

    @Override
    public Map<String, Object> getParameters() {
        return parameters;
    }

    @Override
    public Collection<T> getData() {
        return data;
    }

    @Override
    public String getFileName() {
        return "report" + System.currentTimeMillis();
    }

    @Override
    public Report addParameter(String parameter, Object value) {
        parameters.put(parameter, value);
        return this;
    }

    @Override
    public boolean isCacheable() {
        return false;
    }

}
