
public class StartSchedulerMain {

	public static void main(String[] args) {
		TestCronTrigger trigger = new TestCronTrigger("0 0/1 * * 1/1 ? *", AutoDatePringJob.class);
		trigger.triggerJob();


	}

}
