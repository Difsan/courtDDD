package main.java.org.example.court.business.commons;

import main.java.org.example.court.generic.Command;
import main.java.org.example.court.generic.DomainEvent;

import java.util.List;

public interface UseCaseForCommand <T extends Command>{
    List<DomainEvent> apply(T command);
}
