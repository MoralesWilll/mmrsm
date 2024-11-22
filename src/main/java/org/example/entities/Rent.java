package org.example.entities;

import java.util.Date;

public class Rent {
    private Integer rent_id;
    private Integer client_id;
    private Integer machine_id;
    private String status;
    private Date start_date;
    private Date finish_date;

    public Rent(Integer rent_id, Integer client_id, Integer machine_id, String status, Date start_date, Date finish_date) {
        this.rent_id = rent_id;
        this.client_id = client_id;
        this.machine_id = machine_id;
        this.status = status;
        this.start_date = start_date;
        this.finish_date = finish_date;
    }

    public Rent(Integer client_id, Integer machine_id, Date finish_date) {
        this.client_id = client_id;
        this.machine_id = machine_id;
        this.status = "Active";  // New rents are active by default
        this.start_date = new Date();
        this.finish_date = finish_date;
    }

    public Integer getRent_id() {
        return rent_id;
    }

    public void setRent_id(Integer rent_id) {
        this.rent_id = rent_id;
    }

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public Integer getMachine_id() {
        return machine_id;
    }

    public void setMachine_id(Integer machine_id) {
        this.machine_id = machine_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String nstatus) {
        status = nstatus;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(Date finish_date) {
        this.finish_date = finish_date;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "client_id=" + client_id +
                ", machine_id=" + machine_id +
                ", status=" + status +
                ", start_date=" + start_date +
                ", finish_date=" + finish_date +
                '}';
    }
}
