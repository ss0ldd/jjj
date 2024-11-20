import java.util.List;

public interface UserRepository extends Repository <User>{
    List<User> findAllByAge(Integer age);
}
