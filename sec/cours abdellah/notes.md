MBR : 512 mbs, dont 465 octect de data, le reste est pour la partition table

UEFI is the predecessor of BIOS/MBR, it contains a mini OS with a shell and everything.

UEFI is only a standard, one of its implementations is tiano core.

end to end trust chain

la chaine de confiance commence du cpu,

- suppose we had an intel cpu, we trust intel (the OEM original equipment manufacturer) to be the first intervenant dans la chaine de confiance. (intel guard)

a firmware = micro logiciel. uses secure dfu

- the second part of the trust chain (trust platform module TPM) is the bios/uefi with a signature.
- then the boot loader (grub) + with a signature (it first checks the signature of the step li 9bel)
- the part that follows is the kernel + initramfs + rootfs which is also signed (and also checks signature t boot loader)
- then the last part is application images (which can also be signed)
-

- mac : message auth code
- there is a big debate between the 2 approaches : mac then encrypt, and encrypt then mac

- signature key : public key
- esp partition : efi system partition
- mounted and contains .efi programs : like a grub.efi

- mock management/mmx/key management

