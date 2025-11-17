package org.acme.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.acme.dto.SimulacaoRequestDTO;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.Locale;


@Entity
@Table(name = "simulacao")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SimulacaoEntity {

    public  SimulacaoEntity(SimulacaoRequestDTO request, ProdutoValidadoEntity produto, Double rentabilidadeEfetiva){
        this.produto = produto;
        this.idCliente = request.getClienteId();
        this.prazoMeses= request.getPrazo();
        this.valorFinal = Math.round((request.getValor() * (1 + rentabilidadeEfetiva)) * 100) / 100.0;
        this.valorInvestido = request.getValor();
        this.dataSimulacao = new Date();
        this.rentabilidadeEfetiva = Math.round(rentabilidadeEfetiva * 100) / 100.0;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_produto_validado")
    private ProdutoValidadoEntity produto;

    private Integer idCliente;

    private Double valorInvestido;

    private Double valorFinal ;

    private Double rentabilidadeEfetiva;

    private Integer prazoMeses;

    private Date dataSimulacao;

    @Transactional
    public String getDataSimulacaoFormatada(){
        DateTimeFormatter INPUT_FORMAT =
                DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        ZonedDateTime zdt = ZonedDateTime.parse(this.dataSimulacao.toString(), INPUT_FORMAT);
        return zdt.toInstant().toString();
    }

    public String getDataSimulacaoFormatadaFromBanco() {

        DateTimeFormatter INPUT_FORMAT = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm:ss")
                .appendFraction(ChronoField.MILLI_OF_SECOND, 1, 9, true)
                .toFormatter();

        LocalDateTime ldt = LocalDateTime.parse(this.dataSimulacao.toString(), INPUT_FORMAT);

        return ldt.atZone(ZoneId.systemDefault())
                .toInstant()
                .toString();
    }



}
