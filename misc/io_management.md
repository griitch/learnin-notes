I/O devices are tied to receiving inputs or directing outputs.

Devices have generally 3 types of registers that the cpu can access :

- command registers : used to control the device
- data registers : to transfer the data from and to the device
- status registers : used to check the state of the device

internally the device will contain all of its device-specific logic, this include its micro controller, memory..

## Interconnect

Devices connect with the rest of the system via a controller that is integrated as a part of the device, the controller is connected to a cpu device interconnect such as the PCI (Peripheral component interconnect )

## drivers

OS's support devices with drivers. which are device specific software components that are responsible for interacting directly with the device and providing a higher level interface for the os.

## types of devices

- **block devices** : devices communicates with the driver by sending entire blocks of data, used mainly for storage
- **character devices**: devices communicates with the driver by sending and receiving one single character (1 byte) at a time

## CPU/device interaction

The device registers appear to the cpu as memory locations at a specific physical address, when the cpu writes to any of these locations, the PCI detects that it's destined to a device and routes it accordingly.
This is called `memory mapped i/o`, the portion of memory allocated for devices is controlled by the `base address registers` (BAR), these registers get configured in the boot process according to the pci standard.

The cpu can also access devices via some special instructions, each instruction needs to specify the target device, the i/o port and the value to pass the device, this model is called `i/o port model`

## virtual file system

a layer of abstraction on top of multiple heterogenous file systems, in the application level, the os only deals with the vfs which provides a uniform interface, using file descriptors and other POSIX apis, the underlying fs could be a device fs, or a network fs.. etc
