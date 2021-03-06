package com.project.usmansh.firstactivity.DBClasses;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DatabaseTable(tableName = "teacher_table")
public class TeacherItemTable {



    @Getter
    @Setter
    @DatabaseField(generatedId = true)
    private Long id;

    @Getter
    @Setter
    @DatabaseField
    String index;
    @Getter
    @Setter
    @DatabaseField
    String name;
    @Getter
    @Setter
    @DatabaseField
    String phone;
    @Getter
    @Setter
    @DatabaseField
    String address;



    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
