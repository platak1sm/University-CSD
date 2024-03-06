#include <linux/kernel.h>
#include <asm/current.h>
#include <asm/uaccess.h>
#include <../include/asm-generic/errno-base.h>
#include <../include/linux/sched.h>
#include <../include/linux/d_params.h>

asmlinkage long sys_get_task_params(struct d_params * params){
  printk("Called get_task_params\nManos Platakis 4730\n");
  struct d_params pr;
  if (!access_ok(VERIFY_WRITE, params, sizeof(*params))){
	  return EINVAL;
  }

  copy_from_user(&pr, params, sizeof(struct d_params));

  pr.deadline = params->deadline;
  pr.estimated_runtime = params->estimated_runtime;

  copy_to_user(params, &pr, sizeof(struct d_params));
}
