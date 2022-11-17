package com.examcomplexivo.subastainversaservices.models;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@Entity
@XmlRootElement
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuario_id;

    @Column(unique = true)
    private String nombreUsuario;
    @Column(name = "contrasenia_usuario")
    private String contraseniaUsuario;
}
