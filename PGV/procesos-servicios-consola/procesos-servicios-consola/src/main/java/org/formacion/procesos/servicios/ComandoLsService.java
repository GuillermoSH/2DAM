import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import main.java.org.formacion.procesos.repositories.abstractas.ComandoServiceAbstract;

@Component
public class ComandoLsService extends ComandoServiceAbstract{
    public ComandoLsService() {
        this.setTipo(ProcessType.LS);
    }

    private String expresionRegular = "^((-(la|a|l))|\s*)$";

    @Override
    public void imprimeMensaje() {
        System.out.println("Estoy llamando a ComandoControllerLs");
    }

    @Override
    public boolean validar(String[] arrayComando) {
        if (!super.validarComando()) {
            return false;
        }

        String parametro = arrayComando[1];
        Pattern pattern = Pattern.compile(expresionRegular);
        Matcher matcher = pattern.matcher(parametro);
        if (!matcher.find()) {
            System.out.println("No cumple");
            return false;
        }

        return true;
    }
}
