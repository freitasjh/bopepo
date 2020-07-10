package org.jrimum.bopepo.campolivre;

import org.jrimum.domkee.financeiro.banco.febraban.Titulo;

/**
 * <p>
 * Interface comum para todos os campos livres do Banco Unicred que venham a
 * existir.
 * </p>
 * 
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * 
 * @see AbstractCampoLivre
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
public abstract class AbstractCLBancoUnicred extends AbstractCampoLivre {
    /**
    *
    */
    private static final long serialVersionUID = -2020155324741631945L;

    /**
     * <p>
     * Cria um campo livre com um determinado número de campos
     * </p>
     * 
     * @see AbstractCampoLivre
     * 
     * @param fieldsLength - Número de campos
     */
    protected AbstractCLBancoUnicred(Integer fieldsLength) {

        super(fieldsLength);
    }

    protected static CampoLivre create(Titulo titulo) {

        return new CLBancoReal(titulo);
    }
}