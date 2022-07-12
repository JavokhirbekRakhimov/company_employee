package uz.company.imp;
public interface RepositoryImp<Id> {
    String create(String json);

    String update(String json);

    String delete(Id id);

    String findAll();

    String findById(Id id);

}
