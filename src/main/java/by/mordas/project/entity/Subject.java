package by.mordas.project.entity;


public class Subject extends Entity {
    private long subjectId;
    private String subjectName;

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;

        return subjectId == subject.subjectId && (subjectName != null ? subjectName.equals(subject.subjectName) : subject.subjectName == null);
    }

    @Override
    public int hashCode() {
        int result = (int) (subjectId ^ (subjectId >>> 32));
        if (subjectName!=null) {
           result = 31 * result + subjectName.hashCode();
        }
        return result;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                "} " + super.toString();
    }

}

