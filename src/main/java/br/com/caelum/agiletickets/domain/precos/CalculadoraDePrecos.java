package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {
	
	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		
		int ingressosRestantes = sessao.getTotalIngressos() - sessao.getIngressosReservados();
		boolean cinemaOuShow = sessao.tipoEspetaculo(TipoDeEspetaculo.CINEMA) || sessao.tipoEspetaculo(TipoDeEspetaculo.SHOW);
		if(cinemaOuShow) {
			//quando estiver acabando os ingressos... 
			boolean ultimosIngressos = ingressosRestantes / sessao.getTotalIngressos().doubleValue() <= 0.05;
			if(ultimosIngressos) { 
				preco = multiplicaValorDoIngresso(sessao, 0.10);
			} else {
				preco = sessao.getPreco();
			}
		} else {
			boolean ultimosIngressos = ingressosRestantes / sessao.getTotalIngressos().doubleValue() <= 0.50;
			if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.BALLET)) {
				if(ultimosIngressos) { 
					preco = sessao.addPreco(sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
				} else {
					preco = sessao.getPreco();
				}
				
				if(sessao.getDuracaoEmMinutos() > 60){
					preco = multiplicaValorDoIngresso(sessao, 0.10);
				}
			} else if(sessao.tipoEspetaculo(TipoDeEspetaculo.ORQUESTRA)) {
				if(ultimosIngressos) { 
					preco = multiplicaValorDoIngresso(sessao,0.20);
				} else {
					preco = sessao.getPreco();
				}

				if(sessao.getDuracaoEmMinutos() > 60){
					preco = multiplicaValorDoIngresso(sessao, 0.10);
				}
			}  else {
				//nao aplica aumento para teatro (quem vai é pobretão)
				preco = sessao.getPreco();
			}
		} 

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}
	
	private static BigDecimal multiplicaValorDoIngresso(Sessao sessao,Double multiplicador){
		return sessao.getPreco().multiply(BigDecimal.valueOf(multiplicador));
	}

}