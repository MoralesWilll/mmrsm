package org.example.importer;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.example.controllers.MachineController;
import org.example.entities.Machine;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class MachineImporter {

    public static List<Machine> importMachinesFromExcel(String filePath) throws IOException, InvalidFormatException {
        List<Machine> machineList = new ArrayList<>();
        FileInputStream fis = new FileInputStream(new File(filePath));
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Skip header row
            Cell machineIdCell = row.getCell(0);
            Cell modelCell = row.getCell(1);
            Cell serialNumberCell = row.getCell(2);
            Cell statusCell = row.getCell(3);
            Integer machineId = (int) machineIdCell.getNumericCellValue();
            String model = modelCell.getStringCellValue();
            String serialNumber = serialNumberCell.getStringCellValue();
            String status = statusCell.getStringCellValue();
            Machine machine = new Machine(machineId, model, serialNumber);
            machine.setStatus(status);
            machineList.add(machine);
        }
        workbook.close();
        fis.close();
        return machineList;
    }

    public static void importAndAddMachines(String filePath, Connection connection) throws IOException, InvalidFormatException {
        List<Machine> machines = importMachinesFromExcel(filePath);
        for (Machine machine : machines) {
            MachineController.addMachine(machine, connection);
        }
    }
}
