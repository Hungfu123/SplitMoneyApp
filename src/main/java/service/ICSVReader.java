package service;

import model.Ausgaben;

import java.io.IOException;
import java.util.List;

public interface ICSVReader {
    List<Ausgaben> readAusgaben() throws IOException;
}
