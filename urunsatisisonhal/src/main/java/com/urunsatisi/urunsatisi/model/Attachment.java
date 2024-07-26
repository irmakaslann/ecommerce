package com.urunsatisi.urunsatisi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Attachment {
    @Id
    @GeneratedValue(generator = "uuid")
    private String id;
    private String fileName;
    private String fileType;
    @Lob
    private byte[] data;




}
