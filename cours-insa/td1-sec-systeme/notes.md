the mbr, master boot record, 512 mb, read by the bios (firmware aka micro-logiciel) which is stocked on the rom,
the bios is charged in the ram in a well defined address, (a fixed address !!! that the cpu is programmed to go check it)
The bios knows that a 512 mb sector is a boot record if the last 2 bytes were equal to a magic number = 0xaa55
The bios is a lil bit deprecated, uefi is what is used nowadays, which resembles an OS

first intel cpus (8086) do not offer the mmu module (module li ms2ol ela virtual memroy)
Mmu uses protected mode, 8086 real mode
Pcs boot on real mode but then  switches to protected mode

real mode : addressage physique
protected mode : virtual addresses
MMU modules : translates logocical addresses to physical addresses

in the td
	we fill the first octects with the assembler commands in the file
	the rest with zeroes minus the last 2 bytes
	the 2 last bytes will be the 2 magic numbers


exercice do the same in rust,python,javascript,java,a lang of ur choice

