package com.ravisha.aws.elasticcache.rest.util;

/**
 * Search Criteria can be used for selecting the required records, this objects holds
 * the details like attribute name on which records need to be filtered, attribute
 * value that specifies the  value for the given attribute name, count which tells
 * maximum the number of records to be fetched, offset which tell the offset.
 *
 * @author ravi katta
 */
public class SearchCriteria {
    private String attributeName;
    private String attributeValue;
    private int size;
    private int offset;

    public SearchCriteria(String attributeName, String attributeValue, int size, int offset) {
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
        this.size = size;
        this.offset = offset;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
