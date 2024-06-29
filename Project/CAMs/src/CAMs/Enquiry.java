package CAMs;
/**
 * Represents an enquiry submitted by a student regarding a specific camp.
 *
 * @author Jeevika, Mathangi Teja, Vikaas
 * @version 3.0
 * @since 2023-11-25
 * 
 * @see Query
 * @see Student
 * @see Camp
 */
public class Enquiry extends Query{
	/**
     * The question posed in the inquiry.
     */
	private String question;
	/**
     * The response to the inquiry.
     */
	private String response;
	/**
     * The student who posted the inquiry.
     */
	private Student postedBy;
	/**
     * A boolean flag indicating whether the inquiry has been processed.
     */
	private boolean isProcessed = false;
	/**
     * The camp to which the inquiry is directed.
     */
	private Camp campIncharge;
	/**
     * Constructs an enquiry with the specified question and the student who posted it.
     *
     * @param question The question posed in the inquiry.
     * @param postedBy The student who posted the inquiry.
     */
	public Enquiry(String question, Student postedBy) {
		this.question = question;
		this.postedBy = postedBy;
	}
	/**
     * Sets the processing status of the inquiry to "processed."
     */
    public void setProccessStatus() {
        this.isProcessed = true;
    }
    /**
     * Gets the processing status of the inquiry.
     *
     * @return {@code true} if the inquiry has been processed; {@code false} otherwise.
     */
    public boolean getProccessStatus() {
        return this.isProcessed;
    }
    /**
     * Sets the camp to which the inquiry is directed.
     *
     * @param camp The camp to which the inquiry is directed.
     */
    public void setCampIncharge(Camp camp) {
        this.campIncharge = camp;
    }
    /**
     * Gets the camp to which the inquiry is directed.
     *
     * @return The camp to which the inquiry is directed.
     */
    public Camp getCampIncharge() {
        return this.campIncharge;
    }
    /**
     * Gets the question posed in the inquiry.
     *
     * @return The question posed in the inquiry.
     */
    public String getQuestion() {
        return question;
    }
    /**
     * Sets the question posed in the inquiry.
     *
     * @param question The question posed in the inquiry.
     */
    public void setQuestion(String question) {
        this.question = question;
    }
    /**
     * Gets the student who posted the inquiry.
     *
     * @return The student who posted the inquiry.
     */
    public Student getPostedBy() {
        return postedBy;
    }
    /**
     * Sets the student who posted the inquiry.
     *
     * @param postedBy The student who posted the inquiry.
     */
    public void setPostedBy(Student postedBy) {
        this.postedBy = postedBy;
    }
    /**
     * Gets the response to the inquiry.
     *
     * @return The response to the inquiry.
     */
    public String getResponse() {
        return this.response;
    }
    /**
     * Sets the response to the inquiry.
     *
     * @param response The response to the inquiry.
     */
    public void setResponse(String response) {
        this.response = response;
    }
}