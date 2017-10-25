package ru.gothmog.urbanplanner.model.entities.common;

import ru.gothmog.urbanplanner.model.entities.registry.RegistryItem;

import javax.persistence.*;

/**
 * @author gothmog on 13.09.2017.
 */
@Entity
@Table(name = "file_content")
public class FileContent implements RegistryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "content_file", nullable = false)
    private byte[] contentFile;

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getContentFile() {
        return contentFile;
    }

    public void setContentFile(byte[] contentFile) {
        this.contentFile = contentFile;
    }
}
