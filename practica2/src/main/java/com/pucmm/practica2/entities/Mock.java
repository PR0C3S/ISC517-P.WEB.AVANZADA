package com.pucmm.practica2.entities;

import com.pucmm.practica2.entities.seguridad.Usuario;
import lombok.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.persistence.*;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Mock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name = "";
    private String endPoint="";
    private URI URL;
    private String accessMethod;
    private HttpStatus httpStatus;
    private String responseContentType;
    private String charset = "";
    private String listaHeaders;
    private String httpResponseBody = "";
    private Date creationDate= new Date();
    private String expiryTime;
    private int timeDelay;
    @ManyToOne
    @JoinColumn(name="proyecto", nullable=false)
    private Proyecto proyecto;


    public String fechaVencimiento()
    {
        Calendar newDateTime = Calendar.getInstance();
        newDateTime.setTime(creationDate);

        if(expiryTime.equals("never")){
            newDateTime.add(Calendar.YEAR, 5);
        }else if(expiryTime.equals("year")){
            newDateTime.add(Calendar.YEAR, 1);
        }else if(expiryTime.equals("month")){
            newDateTime.add(Calendar.MONTH , 1);;
        }else if(expiryTime.equals("week")){
            newDateTime.add(Calendar.DAY_OF_YEAR, 7);
        }else{
            newDateTime.add(Calendar.DAY_OF_YEAR, 1);
        }
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
        String date = DATE_FORMAT.format(newDateTime.getTime());
        return date;
    }
    public void SETURL(String user) throws URISyntaxException {
        URL = new URI("http://localhost:8080/practica2/mock/view/"+user+"/"+endPoint);
    }



}
