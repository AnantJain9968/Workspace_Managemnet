package com.st.workspace.management.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "spam_site")
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int siteId;

    private String siteName;
    private String siteLocation;
}
