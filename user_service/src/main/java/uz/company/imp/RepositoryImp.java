package uz.company.imp;
public interface RepositoryImp<Id> {
    String create(String json);

    String update(String json);

    String delete(Id id);

    String findAll(Short size,Short page);

    String findById(Id id);

}
