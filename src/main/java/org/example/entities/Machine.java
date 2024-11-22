package org.example.entities;

public class Machine {
    private Integer machine_id;
    private String model;
    private String serial_number;
    private String status;

    public Machine(Integer machine_id, String model, String serialNumber) {
        this.machine_id = machine_id;
        this.model = model;
        this.serial_number = serialNumber;
        this.status = "Available";
    }

    public Machine(String model, String serialNumber, String status) {
        this.model = model;
        this.serial_number = serialNumber;
        this.status = status;
    }

    public Integer getMachine_id() {
        return machine_id;
    }

    public void setMachine_id(Integer machine_id) {
        this.machine_id = machine_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serialNumber) {
        this.serial_number = serialNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "model='" + model + '\'' +
                ", serial_number='" + serial_number + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
