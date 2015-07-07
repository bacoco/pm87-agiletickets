package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Locale;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Before;
import org.junit.Test;

public class EspetaculoTest {
	
	Espetaculo espetaculo;
	
	@Before
	public void setUp(){
		espetaculo = new Espetaculo();
	}
	
	@Test
	public void criaSessaoDiariaPara1Dia() throws Exception{		
		LocalDate inicio = new LocalDate(2015, 3, 1);
		LocalDate fim = new LocalDate(2015, 3, 1);
		LocalTime horario = new LocalTime(20, 00);
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, Periodicidade.DIARIA);
		
		Assert.assertEquals(inicio.toString(DateTimeFormat.shortDate().withLocale(new Locale("pt", "BR"))), sessoes.get(0).getDia());
		Assert.assertEquals(1, sessoes.size());
	}
	
	@Test
	public void criaSessaoSemanalParaMes() throws Exception{
		LocalDate inicio = new LocalDate(2015, 3, 1);
		LocalDate fim = new LocalDate(2015, 3, 31);
		LocalTime horario = new LocalTime(20, 00);
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, Periodicidade.SEMANAL);
		
		Assert.assertEquals(5, sessoes.size());
		
		LocalDate ultimaSessao = new LocalDate(2015, 03, 29);
		Assert.assertEquals(inicio.toString(DateTimeFormat.shortDate().withLocale(new Locale("pt", "BR"))), sessoes.get(0).getDia());
		Assert.assertEquals(ultimaSessao.toString(DateTimeFormat.shortDate().withLocale(new Locale("pt", "BR"))), sessoes.get(sessoes.size()-1).getDia());
	}
	
	@Test
	public void dataInicioMaiorQueDataFinalRetornaVazio(){
		LocalDate inicio = new LocalDate(2015, 3, 31);
		LocalDate fim = new LocalDate(2015, 3, 1);
		LocalTime horario = new LocalTime(20, 00);
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, Periodicidade.DIARIA);
		
		Assert.assertNull(sessoes);
	}
	
	@Test
	public void criaSessaoDiariaParaMes() throws Exception{
		LocalDate inicio = new LocalDate(2015, 3, 1);
		LocalDate fim = new LocalDate(2015, 3, 31);
		LocalTime horario = new LocalTime(20, 00);
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, Periodicidade.DIARIA);
		
		Assert.assertEquals(31, sessoes.size());
		
		Assert.assertEquals(inicio.toString(DateTimeFormat.shortDate().withLocale(new Locale("pt", "BR"))), sessoes.get(0).getDia());
		Assert.assertEquals(fim.toString(DateTimeFormat.shortDate().withLocale(new Locale("pt", "BR"))), sessoes.get(sessoes.size()-1).getDia());
	}
	

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
}
