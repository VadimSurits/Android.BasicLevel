package geekbrains.AndroidBasicLevel.previousRequests;

import java.util.List;

import geekbrains.AndroidBasicLevel.previousRequests.dao.RequestDao;
import geekbrains.AndroidBasicLevel.previousRequests.model.PreviousRequest;

public class PreviousRequestsSource {

    private final RequestDao requestDao;

    private List<PreviousRequest> previousRequests;

    public PreviousRequestsSource(RequestDao requestDao){
        this.requestDao = requestDao;
    }

    public List<PreviousRequest> getPreviousRequests(){
        if(previousRequests == null){
            LoadPreviousRequests();
        }
        return previousRequests;
    }

    public void LoadPreviousRequests(){
            previousRequests = requestDao.getAllRequests();
    }

    public long getCountPreviousRequests(){
        return requestDao.getCountRequests();
    }

    public void addPreviousRequest(PreviousRequest previousRequest){

        requestDao.insertRequest(previousRequest);
        LoadPreviousRequests();
    }

    public void removePreviousRequest(long id){
        requestDao.deleteRequestbyId(id);
        LoadPreviousRequests();
    }
}
