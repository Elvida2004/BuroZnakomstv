package Entity;


import lombok.Data;

import java.util.List;
@Data
public class ListCandidates {
    private List<CandidateEntity> data;

    public void add(CandidateEntity candidate) {//
    }
}
