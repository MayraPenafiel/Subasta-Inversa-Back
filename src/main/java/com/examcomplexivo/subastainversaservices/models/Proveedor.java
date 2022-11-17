package com.examcomplexivo.subastainversaservices.models;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@Entity
@XmlRootElement
@Table(name = "proveedor")
@PrimaryKeyJoinColumn(name = "id_proveedor", referencedColumnName = "id_persona")
public class Proveedor extends Persona {

    @Column(name = "anios_exp")
    private Integer anios_experiencia;

    @JoinColumn(name = "id_usuario", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

}
