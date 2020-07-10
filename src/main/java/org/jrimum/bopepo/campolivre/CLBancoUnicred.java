/*
 * Copyright 2008 JRimum Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * Created at: 30/03/2008 - 18:09:11
 * 
 * ================================================================================
 * 
 * Direitos autorais 2008 JRimum Project
 * 
 * Licenciado sob a Licença Apache, Versão 2.0 ("LICENÇA"); você não pode usar
 * esse arquivo exceto em conformidade com a esta LICENÇA. Você pode obter uma
 * cópia desta LICENÇA em http://www.apache.org/licenses/LICENSE-2.0 A menos que
 * haja exigência legal ou acordo por escrito, a distribuição de software sob
 * esta LICENÇA se dará “COMO ESTÁ”, SEM GARANTIAS OU CONDIÇÕES DE QUALQUER
 * TIPO, sejam expressas ou tácitas. Veja a LICENÇA para a redação específica a
 * reger permissões e limitações sob esta LICENÇA.
 * 
 * Criado em: 30/03/2008 - 18:09:11
 * 
 */

package org.jrimum.bopepo.campolivre;

import javax.swing.Box.Filler;

import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.texgit.type.component.Fillers;
import org.jrimum.texgit.type.component.FixedField;
import org.jrimum.utilix.Exceptions;
import org.jrimum.utilix.text.Strings;
import org.jrimum.vallia.digitoverificador.Modulo;
import org.jrimum.vallia.digitoverificador.TipoDeModulo;

/**
 * <p>
 * <strong>*** COBRANÇA SEM REGISTRO ***</strong>
 * </p>
 * O campo livre do Banco Real deve seguir esta forma:
 * 
 * <table border="1" cellpadding="0" cellspacing="0" style="border-collapse:
 * collapse" bordercolor="#111111" id="campolivre">
 * <tr>
 * <thead>
 * <th >Posição</th>
 * <th >Tamanho</th>
 * <th >Picture</th>
 * <th>Conteúdo (terminologia padrão)</th>
 * <th>Conteúdo (terminologia do banco)</th> </thead>
 * </tr>
 * <tr>
 * <td>20-23</td>
 * <td>4</td>
 * <td>9(4)</td>
 * <td>Código da agência (sem dígito)</td>
 * <td>Agência - Código da agência do cedente</td>
 * </tr>
 * <tr>
 * <td>24-30</td>
 * <td>7</td>
 * <td>9(7)</td>
 * <td>Código da conta (sem dígito)</td>
 * <td>Conta - Número da conta do cedente</td>
 * </tr>
 * <tr>
 * <td>31-31</td>
 * <td>1</td>
 * <td>9(1)</td>
 * <td>Dígito verificador</td>
 * <td>Digitão - Dígito de cobrança</td>
 * </tr>
 * <tr>
 * <td>32-44</td>
 * <td>13</td>
 * <td>9(13)</td>
 * <td>Número do título(máximo de 13 posições numéricas)</td>
 * <td>Número do banco - Número do título no banco</td>
 * </tr>
 * </table>
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */

class CLBancoUnicred extends AbstractCLBancoUnicred {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5294809022535972391L;

	/**
	 * Tamanho deste campo.
	 */
	private static final Integer FIELDS_LENGTH = 4;

	/**
	 * <p>
	 * Dado um título, cria um campo livre para o padrão do Banco Real.
	 * </p>
	 * 
	 * @param titulo título com as informações para geração do campo livre
	 */
	CLBancoUnicred(Titulo titulo) {

		super(FIELDS_LENGTH);

		this.add(new FixedField<Integer>(titulo.getContaBancaria().getNumeroDaConta().getCodigoDaConta(), 9,
				Fillers.ZERO_LEFT));
		this.add(new FixedField<String>(titulo.getContaBancaria().getNumeroDaConta().getDigitoDaConta(), 1));
		this.add(new FixedField<Integer>(titulo.getContaBancaria().getAgencia().getCodigo(), 4, Fillers.ZERO_LEFT));
		this.add(new FixedField<String>(titulo.getNossoNumero(), 10));
		this.add(new FixedField<String>(calculaDigito(titulo.getNossoNumero()), 1));
	}

	private String calculaDigito(String nossoNumero) {

		String formula = nossoNumero.toString();

		int restoDivisao = Modulo.calculeMod11(formula, 2, 9);

		int restoSubtracao = (11 - restoDivisao);

		if (restoSubtracao > 9) {
			return "0";
		} else {
			return "" + restoSubtracao;
		}

	}

	@Override
	protected void addFields(Titulo titulo) {
		// TODO IMPLEMENTAR
		Exceptions.throwUnsupportedOperationException("AINDA NÃO IMPLEMENTADO!");
	}

	@Override
	protected void checkValues(Titulo titulo) {
		// TODO IMPLEMENTAR
		Exceptions.throwUnsupportedOperationException("AINDA NÃO IMPLEMENTADO!");
	}
}
