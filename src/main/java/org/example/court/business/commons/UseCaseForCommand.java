package org.example.court.business.commons;

import org.example.court.generic.Command;
import org.example.court.generic.DomainEvent;

import java.util.List;

public interface UseCaseForCommand <T extends Command>{
    List<DomainEvent> apply(T command);
}
