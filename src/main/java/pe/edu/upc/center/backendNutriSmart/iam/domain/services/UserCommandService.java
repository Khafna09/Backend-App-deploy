package pe.edu.upc.center.backendNutriSmart.iam.domain.services;

import org.apache.commons.lang3.tuple.ImmutablePair;
import pe.edu.upc.center.backendNutriSmart.iam.domain.model.aggregates.User;
import pe.edu.upc.center.backendNutriSmart.iam.domain.model.commands.SignInCommand;
import pe.edu.upc.center.backendNutriSmart.iam.domain.model.commands.SignUpCommand;

import java.util.Optional;

public interface UserCommandService {
  Optional<ImmutablePair<User, String>> handle(SignInCommand command);
  Optional<User> handle(SignUpCommand command);
}
