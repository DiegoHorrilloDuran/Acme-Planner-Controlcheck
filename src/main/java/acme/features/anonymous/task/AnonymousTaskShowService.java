package acme.features.anonymous.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.entities.Task;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousTaskShowService implements AbstractShowService<Anonymous, Task>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnonymousTaskRepository repository;

	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "start", "end", "workload", "description", "optionalLink", "privacy");
		
	}

	@Override
	public Task findOne(final Request<Task> request) {
		assert request != null;
		
		final Task res;
		
		int id;
		
		id = request.getModel().getInteger("id");
		
		res = this.repository.findOneTaskById(id);
		
		return res;
	}
}
