#include <linux/kernel.h>
#include <asm/current.h>
#include <../include/asm-generic/errno-base.h>
#include <../include/linux/sched.h>
asmlinkage long sys_set_task_params(int dl, int ert){
  printk("Called set_task_params\nManos Platakis 4730\n");
  int dlms = dl*1000;

  if(dlms >= ert && ert>0){
      current->deadline = dl;
      current->estimated_runtime = ert;
      return 0;
  }else
      return EINVAL; /* EINVAL errno =22 */
}
