package com.examcomplexivo.subastainversaservices.models;

//import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@XmlRootElement
@Entity
@Table(name = "servicio")
@AllArgsConstructor
public class Servicio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private long idServicio;

    private String nombre;

    private String descripcion_servicio;

    public Servicio() {

    }
}
