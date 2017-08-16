package cinema.dto.response;

import cinema.dto.Seance;

import java.util.List;

/**
 * Created by Tory on 13.08.2017.
 */
public class SeancesResponse extends Response {

    private List<Seance> seances;

    public List<Seance> getSeances() {
        return seances;
    }

    public void setSeances(List<Seance> seances) {
        this.seances = seances;
    }
}
