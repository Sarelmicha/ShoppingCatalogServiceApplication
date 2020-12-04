package acs.data;

import org.springframework.data.neo4j.core.schema.Node;

public class ProductDetailsEntity {
    private int parts;
    private String manufacturer;
    private boolean collectable;

    public ProductDetailsEntity(){}

    public ProductDetailsEntity(int parts, String manufacturer, boolean collectable)
    {
        this.parts=parts;
        this.manufacturer=manufacturer;
        this.collectable=collectable;
    }

    public void setParts(int parts) {
        this.parts = parts;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setCollectable(boolean collectable) {
        this.collectable = collectable;
    }

    public int getParts() {
        return parts;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public boolean isCollectable() {
        return collectable;
    }
}
