package domain.studyGroupFactory.idProducer;

import storage.exception.DAOException;
import storage.idProducerDAO.IIdProducerDAO;
import storage.idProducerDAO.IdProducerDAO;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;


public class IdProducer {
    private List<Long> idList;
    private IIdProducerDAO idProducerDAO;

    public IdProducer(List<Long> list) {
        ClassLoader classLoader = IdProducer.class.getClassLoader();
        URL url = classLoader.getResource("idProducer");

        this.idProducerDAO = new IdProducerDAO(url.getFile());
        try {
            //idList = idProducerDAO.getList();
            IdProducerDTO idProducerDTO = idProducerDAO.getIdProducerDTO();
            if (idProducerDTO == null) {
                idList = getInitialCollection();
            } else {
                idList = idProducerDTO.IdCollection;
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    private List<Long> getInitialCollection() {
        List<Long> newListId = new LinkedList<>();
        for (long i = 1; i < 100; i++){
            newListId.add(i);
        }
        return newListId;
    }


    public long getId(){
        int k = 0;
        Long resultId = idList.get(k);
        idList.remove(k);
        return resultId;
    }

    public void saveId() throws DAOException {
        IdProducerDTO idProducerDTO = new IdProducerDTO();
        idProducerDTO.IdCollection = idList;
        idProducerDAO.saveIdProducerDTO(idProducerDTO);
    }

}
