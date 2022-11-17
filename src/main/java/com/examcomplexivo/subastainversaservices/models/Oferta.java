package com.examcomplexivo.subastainversaservices.models;

//import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@XmlRootElement
@Table(name = "oferta")
public class Oferta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_oferta")
    private Long idOferta;
    @NotBlank
    @Column(name = "estadi_oferta")
    private String estadoOferta;
    @NotBlank
    @Column(name = "percio_oferta")
    private Double percioOferta;
    @NotBlank
    @Column(name = "fecha_oferta")
    private Date fecha_oferta;
    @NotBlank
    @Column(name = "descripcion_oferta")
    private String descripcion_oferta;
    @NotBlank
    @Column(name = "estado_oferta")
    private boolean estado_oferta;

    @JoinColumn(name = "id_proveedor", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    private Proveedor proveedor;

    @JoinColumn(name = "id_subasta", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    private Subasta subasta;
}
