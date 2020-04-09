package domain.studyGroupFactory.idProducer;

import storage.exception.DAOException;
import storage.idProducerDAO.IdProducerDAO;

import java.util.LinkedList;
import java.util.List;

public class IdProducer {
        private List<Long> idList;

        public IdProducer(String path) throws DAOException {
           idList = initList(path);
        }


        public long getId(){
            int k = 0;
            Long resultId = idList.get(k);
            idList.remove(k);
            return resultId;
        }

        private List<Long> initList(String path) throws DAOException {
            List<Long> list = getDefaultId(path);

            return list;
        }

    private  List<Long> getDefaultId(String path) throws DAOException {
        IdProducerDAO idProducerDAO = new IdProducerDAO(path);

        return idProducerDAO.getDefaultIdProducerDTO();
    }
}
